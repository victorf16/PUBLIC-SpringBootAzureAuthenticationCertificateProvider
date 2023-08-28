# Aplicação Cliente, Login com Certificado d utilizando Oauth 2 com azure AD #

## 1 Premissas e considerações

 * Para este tutorial utilizamos o Azure APIM para realizar a validaçao do JWT TOken, a aplicação JAVA presente neste repositório tem o objetivo de realizar o cálculo de 2 números e retornar para o usuári opós uma chamada post

 * É necessário executar com JAVA 11 ou superior conforme especificação no arquivo POM.xml do projeto

 * Para o projeto, foi considerado uma aplicação cliente que irá se comunicar com um aplicação backend, para maiores detalhes da aplicação consumer veja o repo > https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificate, que funcionará como uma calculadora que realiza uma SOMA
  
 * Consideramos o Azure APIM, arquivos de implementação terraform armazenados na pasta Templates > IaC

 * 
## Arquitetura final

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificate-/assets/28166733/f4d87fab-ab47-40b3-a6e3-680b58e3ed1c)

## arquitetura de deploy

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/0128246e-f550-4f4b-b4e6-21b756f39b03)

# AZURE APIM
1. Implemente o APIM conforme terraform terraform.apim.tf localizados no diretóro te templates de infraestrutura
2. Após criar o APIM (demora em torno de 25 minutos) você deve criar uma nova API clicando na blade de APIs e criar uma nova API em +Add API ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/74eb6831-ecf0-458a-b8a9-3df010268900)

3. Após insira as informações necessárioas para o seu entpoint, neste exemplo utilize i o displayname soma
 ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/cc8d2fc2-07aa-452d-91e7-47d2e1c09875)


4. Habilite o Oauth2
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/e53af5cd-91c8-44ab-9884-e1059f7e2c56)

5 Após navegue até all operations e clique em base 
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/1fa7a428-b292-4f31-bdb1-27ea8b540b4c)
