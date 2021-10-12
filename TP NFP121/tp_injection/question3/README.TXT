bean.id.1=serviceLocator
serviceLocator.class=service_locator.ServiceLocator
serviceLocator.property.1=container
serviceLocator.property.1.param.1=strategy
serviceLocator.property.2=container
serviceLocator.property.2.param.1=pubsub

bean.id.2=strategy
strategy.class=container.FileSystemPropsApplicationContext
strategy.property.1=fileName
strategy.property.1.param.1=./question2/StrategyConfig.txt



bean.id.3=pubsub
pubsub.class=container.FileSystemPropsApplicationContext
pubsub.property.1=fileName
pubsub.property.1.param.1=./question2/PubSubConfig.txt



