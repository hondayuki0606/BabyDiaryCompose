#java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i yaml/pet.yaml
#java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i yaml/store.yaml
java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i yaml/user.yaml
cp -r out/src/main/kotlin/org/openapitools/client/apis ../../app/src/main/java/com/example/babydiarycompose/
cp -r out/src/main/kotlin/org/openapitools/client/models ../../app/src/main/java/com/example/babydiarycompose/
rm -r ./out