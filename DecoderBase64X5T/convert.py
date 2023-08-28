# o codigo abaixo converte o thumbprint de um certificado em um  base 64
import base64
x = "84E05C1D98BCE3A5421D225B140B36E86A3D5534"
x5t = base64.b64encode(bytearray.fromhex(x))
print(x5t.decode())