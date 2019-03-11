# AndroidProjectCore
[![](https://jitpack.io/v/logicfanlab/AndroidProjectCore.svg)](https://jitpack.io/#logicfanlab/AndroidProjectCore)

Core, base and utility functionalities required by an android project

## How to publish new version on jitpack
1. increment version code and name in library build.gradle
2. commit changes
3. use this git snippet to add release tag (or if you are using sourcetree just tag the release!) :
```
git tag -a 1.0 -m "v1.0"
git push origin 1.0
```
4. hit the jitpack image at the beginning of this readme
5. wait for jitpack to build your artifact
6. if build failed (there might be a red icon), fix the errors and repeat step 1-6
7. hit the **Get it** button
8. add release title and changelog to Github release section

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
    implementation "com.github.logicfanlab:AndroidProjectCore:$versions.logicfanCore"
}
```
