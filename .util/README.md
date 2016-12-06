# Make a release to `X.Y.Z`

## Update the project

### In the main project
- Before change the version, ensure that you have installed the current version in your local repository : otherwise, you will have some issues in releasing Eclipse and Benchmarks.
`mvn clean install -DskipTests`

- Define the new version.
`mvn versions:set -DnewVersion=X.Y.Z`

- Comment the timestamp in bundle-plugin at `Version`

- Install the new version in your local repository.
`mvn clean install -DskipTests`

### In Eclipse project

- As previously, ensure that you have installed the current version in your local repository.
`mvn clean install -DskipTests`

- Update the `parent.version` in the root `pom.xml` according to the new version, as previously set, but not others.

- Define the new version.
`mvn tycho-versions:set-version -DnewVersion='X.Y.Z'` (if it's a SNAPSHOT, add `.qualifier` instead of `-SNAPSHOT`)
  _It will remove the `parent.version` in the root `pom.xml` because it's the same of the parent. Re-set it to the current version._

- Update feature.xml in features, and remove `.qualifier` for NeoEMF dependencies.

- Install the new version in your local repository.
`mvn clean install -DskipTests`

### In benchmarks project

- As previously, ensure that you have installed the current version in your local repository.
`mvn clean install -DskipTests`

- Update the `parent.version` in the root `pom.xml` according to the new version, as previously set, but not others.

- Define the new version.
`mvn versions:set -DnewVersion=X.Y.Z`

- Install the new version in your local repository.
`mvn clean install -DskipTests`

## Publish the release in Github

- Commit (without push) the modifications
- Tag the previous commit with name `vX.Y.Z`
- Create a new branch named `vX.Y.Z`

# Deploy a release

Only the main project have to be deployed in Maven central.
OSS-RH has some requirement to accept the release (see link).
One of them is the signature of artifacts ! Firstly, you need to create a GPG key

`mvn clean package javadoc:jar source:jar-no-fork gpg:sign install deploy -DskipTests`
