#
# Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
#
# All rights reserved. This program and the accompanying materials are made
# available under the terms of the Eclipse Public License v2.0 which accompanies
# this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
#

#
# USAGE:
#
# docker build -t neoemf .
# docker run -v {LOCAL_DIR}:/root/ws neoemf [parameters] init
# docker run -v {LOCAL_DIR}:/root/ws neoemf [parameters] [options]
#

FROM debian:latest

ENV DEBIAN_FRONTEND noninteractive
ENV DEBCONF_TERSE true

# The default base directory for storing models
ENV NEOEMF_HOME /root/ws

WORKDIR /root

# Install common dependency (required by 'add-apt-repository')
RUN apt-get update -qq \
 && apt-get install -q --no-install-recommends -y \
    software-properties-common \

# Add the JDK8 repository
 && add-apt-repository -y 'deb http://ppa.launchpad.net/webupd8team/java/ubuntu xenial main' \
 && apt-get update -qq \

# Auto-accept the Oracle JDK license
 && echo 'oracle-java8-installer shared/accepted-oracle-license-v1-1 select true' | /usr/bin/debconf-set-selections \

# Install JDK8 & build tool
 && apt-get install -q --no-install-recommends -y --allow-unauthenticated \
    oracle-java8-installer \
    oracle-java8-set-default \
    maven \

# Clean the apt cache
 && rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Copy the project
ADD . src

# Build the main project
RUN mvn -B install -DskipTests -Dmaven.javadoc.skip=true -pl !neoemf-data/hbase,!neoemf-tests -f src/pom.xml

# Build benchmarks
RUN mvn -B package -DskipTests -Dmaven.javadoc.skip=true -f src/benchmarks/pom.xml

# Move the resulting artifacts
RUN mv -f src/benchmarks/core/target/exec/* . \

# Remove build files
 && rm -rf src .m2 /tmp/* /var/tmp/*

CMD ["-help"]
ENTRYPOINT ["java", "-jar", "benchmarks.jar"]