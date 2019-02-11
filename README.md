# AndroidProjectCore
[![](https://jitpack.io/v/logicfanlab/AndroidProjectCore.svg)](https://jitpack.io/#logicfanlab/AndroidProjectCore)

Core, base and utility functionalities required by an android project

## How to publish new version on jitpack
1. increment version code and name in library build.gradle
2. commit changes
3. use this git snippet to add release tag:
```
git tag -a 1.0 -m "v1.0"
git push origin 1.0
```
That's it! jitpack will package your build automatically.

## How to use in your project
top level build.gradle
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
module level build.gradle
```
dependencies {
    implementation "com.github.logicfanlab:AndroidProjectCore:${versions.logicfanCore}"
}
```
