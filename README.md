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

´´´´ java





<!-- 

    IMPORTANT: 

    - Policy elements can appear only within the <inbound>, <outbound>, <backend> section elements. 

    - To apply a policy to the incoming request (before it is forwarded to the backend service), place a corresponding policy element within the <inbound> section element. 

    - To apply a policy to the outgoing response (before it is sent back to the caller), place a corresponding policy element within the <outbound> section element. 

    - To add a policy, place the cursor at the desired insertion point and select a policy from the sidebar. 

    - To remove a policy, delete the corresponding policy statement from the policy document. 

    - Position the <base> element within a section element to inherit all policies from the corresponding section element in the enclosing scope. 

    - Remove the <base> element to prevent inheriting policies from the corresponding section element in the enclosing scope. 

    - Policies are applied in the order of their appearance, from the top down. 

    - Comments within policy elements are not supported and may disappear. Place your comments between policy elements or at a higher level scope. 

-->
<policies>
    <inbound>
        <base />
        <authentication-certificate thumbprint="A0C8F954707B196CE31F474ADD0A1FD01AE97567" />
        <validate-jwt header-name="Authorization" failed-validation-httpcode="401" failed-validation-error-message="Unauthorized. Access token is missing or invalid.">
            <openid-config url="https://login.microsoftonline.com/Seu-Tenant/v2.0/.well-known/openid-configuration" />
            <issuers>
                <issuer>https://sts.windows.net/eu-Tenant-ID</issuer>
            </issuers>
            <required-claims>
                <claim name="aud">
                    <value>Endereço do Client ID do APP backend1</value>
                </claim>
            </required-claims>
        </validate-jwt>
    </inbound>
    <backend>
        <base />
    </backend>
    <outbound>
        <base />
    </outbound>
    <on-error>
        <base />
    </on-error>
</policies>





```
clique em Add endpoint e insira o endereço web da sua aplicação backand  odne o código sera implementado, exemplo, no nosso caso vamos executar nosso arquivo terraform para construir o weball atravé de managed identity
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/584ea7e8-3961-4686-a31a-28b3c20fb086)
´´



