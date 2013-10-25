package com.example;

import dagger.Module;

@Module(injects = {Main.class, MainUsingProvider.class})
public class MainModule {
}