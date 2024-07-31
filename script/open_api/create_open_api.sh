#!/bin/bash
#java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i yaml/pet.yaml
#java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i yaml/store.yaml
#java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i 
java -jar openapi-generator-cli.jar generate \
    --input-spec yaml/user.yaml \
    --generator-name kotlin \
    --output ./out \
    --package-name com.example.babydiarycompose \
    --group-id com.example \
    --api-package com.example.babydiarycompose.api \
    --invoker-package com.example.babydiarycompose.invoker \
    --model-package com.example.babydiarycompose.models 
#java -jar openapi-generator-cli.jar generate -i yaml/user.yaml -g kotlin -o out --package-name com.example.babydiarycompose --group-id com.example --api-package com.example.babydiarycompose.api --model-package com.example.babydiarycompose.models

#java -jar openapi-generator-cli.jar generate \
#  -i yaml/user.yaml  \
#  -g kotlin \
#  -o out \
#  -c ./openapi.config \
#  --group-id com.example  \
#  --artifact-id sample-api-generated  \
#  --artifact-version 0.0.1-SNAPSHOT  \
#  --api-package com.example.api.controller  \
#  --model-package com.example.api.model
cp -r out/src/main/kotlin/com/example/babydiarycompose/api ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/models ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/invoker ../../app/src/main/java/com/example/babydiarycompose/
rm -r ./out