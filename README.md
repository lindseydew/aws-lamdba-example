## Getting set up 

To use the SAM CLI, you need the following tools.

* SAM CLI - [Install the SAM CLI](https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html)
* Java11 - [Install the Java 11](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html)
* Docker - [Install Docker community edition](https://hub.docker.com/search/?type=edition&offering=community)

## Running the App
Make sure you have docker running

### Command line
#### Invoke calling using API Gateway
- `sam build && sam local start-api`
- `curl http://localhost:3000/hello/developer`
#### Invoke lambda directly
- `sam build`
- `sam local invoke HelloWorldFunction --event events/get.json`

## With IntelliJ
https://medium.com/innomizetech/how-to-create-debug-and-deploy-aws-serverless-application-with-java-using-intellij-idea-ed0e07d75fe4

### Running through IntelliJ
- Copy AWS credentials for an account using Janus
- Click the lambda icon on in `App.java`
- Click `edit configurations`
- On the `AWS Connection` tab select the profile of the account you used from Janus
- On the `Configuration` tab select `fromHandler`. Under `Input` select `text`
and copy and paste the contents of `get.json` from the events folder and save
- Then run or debug from the console

### Deploying
- Run gradle buildZip. This will produce a zip file in build/distributions
- Create a lambda function
- Upload the zip to the lambda function
- Set the path to the handler to be helloworld.App::handleRequest
