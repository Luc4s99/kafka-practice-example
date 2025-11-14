When a record is inserted into the main service, it will be replicated to the branches via Apache Kafka. These branches will insert the records into their respective tables, synchronizing all tables at the end.

<img width="691" height="411" alt="Untitled Diagram drawio" src="https://github.com/user-attachments/assets/f15e70ce-0850-47a0-8e31-68083b70fb36" />
