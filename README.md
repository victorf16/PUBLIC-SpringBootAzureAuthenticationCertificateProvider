# Aplicação Cliente, Login com Certificado d utilizando Oauth 2 com azure AD #

## 1 Premissas e considerações

 * Para este tutorial utilizamos o Azure APIM para realizar a validaçao do JWT TOken, a aplicação JAVA presente neste repositório tem o objetivo de realizar o cálculo de 2 números e retornar para o usuári opós uma chamada post

 * É necessário executar com JAVA 11 ou superior conforme especificação no arquivo POM.xml do projeto

 * Para o projeto, foi considerado uma aplicação cliente que irá se comunicar com um aplicação backend, para maiores detalhes da aplicação consumer veja o repo > https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificate, que funcionará como uma calculadora que realiza uma SOMA
  
 * Consideramos o Azure APIM, arquivos de implementação terraform armazenados na pasta Templates > IaC

 * 
# Arquitetura final

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificate-/assets/28166733/f4d87fab-ab47-40b3-a6e3-680b58e3ed1c)

# arquitetura de deploy

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/0128246e-f550-4f4b-b4e6-21b756f39b03)
