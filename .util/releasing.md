# Make a release to `X.Y.Z`

## Upgrade to a RELEASE version

### In the main project
- Before change the version, ensure that you have installed the current version in your local repository : otherwise, you will have some issues in releasing Eclipse and Benchmarks.
```
mvn clean install -DskipTests
```

- Define the new version.
```
mvn versions:set -DnewVersion='X.Y.Z'
```

- (Un)Comment the timestamp in `maven-bundle-plugin` at `Bundle-Version`, configured with `.${maven.build.timestamp}`.

- Install the new version in your local repository.
```
mvn clean install -DskipTests
```

### In Eclipse project

- As previously, ensure that you have installed the current version in your local repository.
```
mvn clean install -DskipTests
```

- Update the `parent.version` in the root `pom.xml` according to the new version, as previously set, but not others.

- Define the new version.
  
  __NOTE:__ If it's a SNAPSHOT, add `.qualifier` instead of `-SNAPSHOT`.
  
  __NOTE2:__ It will remove the `parent.version` in the root `pom.xml` because it's the same of the parent. Re-set it to the current version.

```
mvn tycho-versions:set-version -DnewVersion='X.Y.Z'
```

- Update `feature.xml` in features and `category.xml` in the update-site: remove `.qualifier` for NeoEMF dependencies.

- Install the new version in your local repository.
```
mvn clean install -DskipTests
```

### In benchmarks project

The procedure is the same as in the main project.

## Publish the release in Github

- Commit __(without push)__ the modifications
- Tag the commit with name `vX.Y.Z`
- Create a new branch named `vX.Y.Z`

# Deploy a release

__NOTE:__ Only the main project have to be deployed in Maven central !

OSS-RH has some [requirements][ossrh-guide] to accept the release.
One of them is the signature of the artifacts: A full description is available [here][oss-signing], describing how to create his key.

Maven must be configured to handle signature and deployment.
The following `settings.xml` file must be filled and placed at the root of your `.m2` directory:

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <servers>
        <server>
            <id>ossrh</id>
            <username>{OSS-USERNAME}</username>
            <password>{OSS-PASSWORD}</password>
        </server>
    </servers>
    <profiles>
        <profile>
            <id>deployment</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <gpg.executable>gpg2</gpg.executable>
                <gpg.passphrase>{GPG-PASSPHRASE}</gpg.passphrase>
            </properties>
        </profile>
    </profiles>
</settings>
```

Once is done, you can deploy your artifacts on Maven Central with the following:
```
mvn clean package javadoc:jar source:jar-no-fork gpg:sign install deploy -DskipTests
```

Artifacts are now staged and wait for validation and deployment on the [Nexus Repo Manager][oss-sonartype] (Note that staged artifacts, are not definitive, they can be removed/cancelled): A full description of different steps is available [here][oss-sonartype-release].

# Upgrade to a SNAPSHOT version

Once you have deployed yor artifacts, to avoid overwritten your release, you must upgrade your project in a SNAPSHOT state.
Repeat the several steps described in the [Upgrade to a RELEASE version](#upgrade-to-a-release-version) section by adding `-SNAPSHOT` or `.qualifier`.

Once is done, you can now commit and push the changes in GitHub.

[ossrh-guide]: http://central.sonatype.org/pages/ossrh-guide.html
[oss-sonartype]: https://oss.sonatype.org
[oss-signing]: http://central.sonatype.org/pages/working-with-pgp-signatures.html
[oss-sonartype-release]: http://central.sonatype.org/pages/releasing-the-deployment.html
