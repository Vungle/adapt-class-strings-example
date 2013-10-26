# Overview

Example showing incompatibility between Dagger and Proguard -adaptclassstrings.

Dagger uses fully-qualified class names embedded in strings with other characters.

Proguard -adaptclassstrings option only replaces fully-qualified class names in strings all by themselves (without extraneous characters).

Therefore, it seems Proguard cannot be used to obfuscate most classes using Dagger dependency injection.

### Steps to reproduce
<pre>
  $  git clone git@github.com:Vungle/adapt-class-strings-example.git
  $  cd adapt-class-strings-example
  adapt-class-strings-example $  mvn clean install
  adapt-class-strings-example $  java -cp /path/to/javax.inject-1.jar:/path/to/dagger-1.1.0.jar\
    :target/adapt-class-strings-example-1.0.0-SNAPSHOT.jar com.example.Main
</pre>

> com.example.a<br/>
  This contains com.example.Dependency

A string consisting of only the fully-qualified classname (com.example.Dependency) is replaced with the 
obfuscated class name (com.example.a).<br/>
However, a string containg the classname amidst characters is not replaced.

<pre>
  adapt-class-strings-example $  java -cp /path/to/javax.inject-1.jar:/path/to/dagger-1.1.0.jar\
    :target/adapt-class-strings-example-1.0.0-SNAPSHOT.jar com.example.MainUsingProvider
</pre>
> Exception in thread "main" java.lang.IllegalStateException: Errors creating object graph:<br/>
  Unable to create binding for com.example.MainUsingProvider required by class com.example.d<br/>
  at dagger.internal.ThrowingErrorHandler.handleErrors(ThrowingErrorHandler.java:34)<br/>
  at dagger.internal.Linker.linkRequested(Linker.java:146)<br/>
  at dagger.ObjectGraph$DaggerObjectGraph.getInjectableTypeBinding(ObjectGraph.java:288)<br/>
  at dagger.ObjectGraph$DaggerObjectGraph.get(ObjectGraph.java:249)<br/>
  at com.example.MainUsingProvider.main(MainUsingProvider.java:14)

Dagger module (com.example.MainModule = com.example.d) cannot instantiate com.example.MainUsingProvider 
because it is trying to instantiate the obfuscated class using the unobfuscated name.<br/>
This is because Dagger uses strings like 'members/com.example.MainUsingProvider' and 
'javax.inject.Provider<com.example.Dependency>' and Proguard with the -adaptclassstrings flag will not 
replace obfuscated class names.  Either Dagger or Proguard might be modified to workaround this issue.
