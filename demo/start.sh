#!/bin/bash
./mvnw spring-boot:run "-Drun.jvmArguments='-Dserver.port=8081'" -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE};