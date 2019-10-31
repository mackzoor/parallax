import socket

if __name__ == '__main__':
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(('localhost', 5000))

    while 1:
        data = client_socket.recv(1024)
        print('Received', repr(data))
