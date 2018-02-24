[![](https://jitpack.io/v/banklink/paysystem.svg)](https://jitpack.io/#banklink/paysystem) [![API](https://img.shields.io/badge/API-19%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=19)

# How to

To get a Git project into your build:

### Step 1. Add the JitPack repository to your build file

gradle
maven
sbt
leiningen
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
### Step 2. Add the dependency
	dependencies {
	        compile 'com.github.banklink:paysystem:1.0'
	}
