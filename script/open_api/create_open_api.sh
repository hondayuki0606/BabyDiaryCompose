java -jar openapi-generator-cli.jar generate -g kotlin --additional-properties=prependFormOrBodyParameters=true -o out -i yaml/test.yaml
rm -r ./out