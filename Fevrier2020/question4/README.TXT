#
verbose=true

bean.id.1=mailBox
mailBox.class=question4.MailBox

bean.id.2=mailBoxPersistent
mailBoxPersistent.class=question4.MailBoxPersistent
mailBoxPersistent.property.1=fileName
mailBoxPersistent.property.1.param.1=mailbox.ser
mailBoxPersistent.property.2=persistentMode
mailBoxPersistent.property.2.param.1=serializer

bean.id.3=mailBoxNotification
mailBoxNotification.class=question4.MailBoxNotification
mailBoxNotification.property.1=mailBox
mailBoxNotification.property.1.param.1=mailBox

bean.id.4=serializer
serializer.class=question3.SerializationMode