package com.example;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.ObjectGraph;

public class MainUsingProvider {
	@Inject
	Provider<Dependency> dependencyProvider;

	public static void main(String... args) {
		final ObjectGraph objectGraph = ObjectGraph.create(MainModule.class);
		final MainUsingProvider main = objectGraph.get(MainUsingProvider.class);
		System.out.println(main.dependencyProvider.get().getClassName());
		System.out.println(main.dependencyProvider.get().getSentenceContainingClassName());
	}
}
