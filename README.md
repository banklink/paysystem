[![](https://img.shields.io/badge/paysystem-1.0-green.svg)](https://github.com/banklink/paysystem) 
[![](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)
[![](https://img.shields.io/badge/License-Apache--2.0-brightgreen.svg)](https://github.com/banklink/paysystem/blob/master/LICENSE.txt) 

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
