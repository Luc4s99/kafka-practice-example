When a record is inserted into the main service, it will be replicated to the branches via Apache Kafka. These branches will insert the records into their respective tables, synchronizing all tables at the end.

![Untitled Diagram drawio](https://github.com/user-attachments/assets/a9cedd4d-bdf6-4398-8de7-5c8a99c93b11)
