# CIS3319DESLab1

## Structure
There are two runnable classes, a sender and receiver. The sender class takes a few inputs for connecting through a socket, generates a key, and sends the cipher-text. The receiver class must be running before the sender transmits the message and it will wait until a message is received. At that point it will prompt for the generated key which it will use to decrypt the received message.

The DesEncrypter object handles key encryption, and decryption. It only needs to be instantiated given a key in utf format to utilize its functions.

