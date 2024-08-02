#!/bin/bash
java -jar openapi-generator-cli.jar generate \
    --input-spec yaml/user.yaml \
    --library jvm-retrofit2 \
    --generator-name kotlin \
    --output ./out \
    --package-name com.example.babydiarycompose \
    --group-id com.example \
    --api-package com.example.babydiarycompose.api \
    --invoker-package com.example.babydiarycompose.invoker \
    --model-package com.example.babydiarycompose.models \
#    --additional-properties collectionType=list,dateLibrary=java8,enumPropertyNaming=UPPERCASE,serializationLibrary=kotlinx_serialization,useCoroutines=true
cp -r out/src/main/kotlin/com/example/babydiarycompose/api ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/models ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/infrastructure ../../app/src/main/java/com/example/babydiarycompose/
rm -r ./out