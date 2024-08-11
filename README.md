# Shadow

![GitHub all releases](https://img.shields.io/github/downloads/7orivorian/Shadow/total?style=flat-square)
![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/7orivorian/Shadow?style=flat-square)
[![](https://jitci.com/gh/7orivorian/Shadow/svg)](https://jitci.com/gh/7orivorian/Shadow)

Json config library.

# Importing

### Maven

Include JitPack in your maven build file.

```xml

<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Add Shadow as a dependency.

```xml

<dependency>
    <groupId>com.github.7orivorian</groupId>
    <artifactId>Shadow</artifactId>
    <version>0.1.0</version>
</dependency>
```

### Gradle

Add JitPack to your root `build.gradle` at the end of repositories.

```gradle
repositories {
    maven {
        url 'https://jitpack.io'
    }
}
```

Add Shadow as a dependency.

```gradle
dependencies {
    implementation 'com.github.7orivorian:Shadow:0.1.0'
}
```

### Other

Download a `.jar` file
from [releases](https://github.com/7orivorian/Shadow/releases/tag/0.1.0).

# Building

* Clone this repository
* Run `mvn package`

Packaged jars can be found in the `./target/` directory.

# License

[Shadow is licensed under MIT](./LICENSE)

### MIT License Summary:

The MIT License is a permissive open-source license that allows you to use,
modify, and distribute the software for both
personal and commercial purposes. You are not required to share your changes,
but you must include the original
copyright notice and disclaimer in your distribution. The software is provided "
as is," without any warranties or
conditions.