#
# Copyright (c) 2013-2017 Atlanmod, Inria, LS2N, and IMT Nantes.
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
# docker build -t neoemf .
# docker run -v {LOCAL_DIR}:/root/ws neoemf [parameters] init
# docker run -v {LOCAL_DIR}:/root/ws neoemf [parameters] [options]
#

FROM maven:alpine

ENV NEOEMF_HOME /root/ws
VOLUME $NEOEMF_HOME

WORKDIR /tmp/neoemf
COPY . .
RUN mvn -B install -DskipTests -pl !neoemf-data/hbase,!neoemf-tests  \
 && mvn -B package -DskipTests -f benchmarks/pom.xml -Duberjar.directory=/root/ \
 && rm -rf /root/.m2/* /tmp/*

WORKDIR /root
CMD ["-help"]
ENTRYPOINT ["java", "-jar", "benchmarks.jar"]