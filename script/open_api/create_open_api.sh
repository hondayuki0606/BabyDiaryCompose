#!/bin/bash


# babydiarycompose の API Client のパッケージ
BABY_DIARY_COMPOSE_PACKAGE=com.example.babydiarycompose

# プロジェクト内の API Client のディレクトリ
API_CLIENT_DIR=$SCRIPT_DIR/apiclient/src/main/kotlin/dev/honwakalab/linkmark/apiclient
echo --package-name $BABY_DIARY_COMPOSE_PACKAGE
echo --api-package $BABY_DIARY_COMPOSE_PACKAGE.api
echo --invoker-package $BABY_DIARY_COMPOSE_PACKAGE.invoker
echo --model-package $BABY_DIARY_COMPOSE_PACKAGE.models
java -jar openapi-generator-cli.jar generate \
    --input-spec yaml/user.yaml \
    --library jvm-retrofit2 \
    --generator-name kotlin \
    --output ./out \
    --package-name com.example.babydiarycompose \
    --group-id com.example \
    --package-name $BABY_DIARY_COMPOSE_PACKAGE \
    --api-package $BABY_DIARY_COMPOSE_PACKAGE.api \
    --invoker-package $BABY_DIARY_COMPOSE_PACKAGE.invoker \
    --model-package $BABY_DIARY_COMPOSE_PACKAGE.models \
    --additional-properties collectionType=list,dateLibrary=java8,enumPropertyNaming=UPPERCASE,serializationLibrary=kotlinx_serialization,useCoroutines=true
    #useCoroutines=true はsuspend付与
    #dateLibrary=java8 は変化なし
    #enumPropertyNaming=UPPERCASE は変化なし
    #serializationLibrary=kotlinx_serialization は@SerializableをつけることができJsonのエンコード、デコードが容易にできるようになる
cp -r out/src/main/kotlin/com/example/babydiarycompose/api ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/models ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/infrastructure ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/com/example/babydiarycompose/auth ../../app/src/main/java/com/example/babydiarycompose/
rm -r ./out