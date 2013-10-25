Example showing incompatibility between Dagger and Proguard -adaptclassstrings.

Dagger uses fully-qualified class names embedded in strings with other characters.

Proguard -adaptclassstrings option only replaces fully-qualified class names in strings all by themselves (without extraneous characters).
