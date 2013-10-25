package com.example;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.ObjectGraph;

public class Main {
	@Inject
	Dependency dependency;

//	@Inject
//	Provider<Dependency> dependencyProvider;

	public static void main(String... args) {
		final ObjectGraph objectGraph = ObjectGraph.create(MainModule.class);
		final Main main = objectGraph.get(Main.class);
		System.out.println(main.dependency.getClassName());
		System.out.println(main.dependency.getSentenceContainingClassName());
//		System.out.println(main.dependencyProvider.get().getClassName());
//		System.out.println(main.dependencyProvider.get().getSentenceContainingClassName());
	}
}
