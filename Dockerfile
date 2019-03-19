#
# Copyright (c) 2013 Atlanmod.
#
# All rights reserved. This program and the accompanying materials are made
# available under the terms of the Eclipse Public License v2.0 which accompanies
# this distribution, and is available at https://www.eclipse.org/legal/epl-2.0/
#
# This Source Code may also be made available under the following Secondary
# Licenses when the conditions for such availability set forth in the Eclipse
# Public License, v. 2.0 are satisfied: GNU General Public License, version 3.
#

#
# USAGE:
#
# docker build -t neoemf-local .
# docker run --rm -v {LOCAL_DIR}:/root/ws neoemf [parameters] init
# docker run --rm -v {LOCAL_DIR}:/root/ws neoemf [parameters] [options] regex
#
# If you want profiling with JProfiler: define the port binding (8849) and add the following parameter:
# -jvmArgsPrepend -agentpath:/usr/local/jprofiler/bin/linux-x64/libjprofilerti.so=nowait,port=8849
#
# If you want JSON results, add the following parameter (parent directories must exist):
# -rf json -rff /root/ws/benchmarks/results/${FILENAME}
#

FROM maven:alpine

ENV NEOEMF_HOME /root/ws
VOLUME $NEOEMF_HOME

# Build project & benchmarks
WORKDIR /tmp/neoemf
COPY . .
RUN mvn -B install -DskipTests -pl !neoemf-tests  \
 && mvn -B package -DskipTests -f benchmarks/pom.xml -Duberjar.directory=/root/ \
 && rm -rf /root/.m2/* /tmp/*

# Install JProfiler agent
RUN wget https://download-keycdn.ej-technologies.com/jprofiler/jprofiler_linux_10_1_5.tar.gz -P /tmp/ \
 && tar -xzf /tmp/jprofiler_linux_10_1_5.tar.gz -C /usr/local \
 && mv /usr/local/jprofiler10.1.5 /usr/local/jprofiler \
 && rm /tmp/jprofiler_linux_10_1_5.tar.gz

EXPOSE 8849

# Configure container
WORKDIR /root
CMD ["-help"]
ENTRYPOINT ["java", "-jar", "neoemf-benchmarks.jar"]