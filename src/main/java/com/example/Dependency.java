package com.example;

import javax.inject.Inject;

public class Dependency {
	@Inject
	Dependency() {}

	public String getClassName() {
		return "com.example.Dependency";
	}

	public String getSentenceContainingClassName() {
		return "This contains com.example.Dependency";
	}
}