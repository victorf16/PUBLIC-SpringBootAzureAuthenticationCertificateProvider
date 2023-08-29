# Aplicação Cliente, Login com Certificado d utilizando Oauth 2 com azure AD #

## 1 Premissas e considerações

 * Para este tutorial utilizamos o Azure APIM para realizar a validaçao do JWT TOken, a aplicação JAVA presente neste repositório tem o objetivo de realizar o cálculo de 2 números e retornar para o usuári opós uma chamada post

 * É necessário executar com JAVA 11 ou superior conforme especificação no arquivo POM.xml do projeto

 * Para o projeto, foi considerado uma aplicação cliente que irá se comunicar com um aplicação backend, para maiores detalhes da aplicação consumer veja o repo > https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificate, que funcionará como uma calculadora que realiza uma SOMA
  
 * Consideramos o Azure APIM, arquivos de implementação terraform armazenados na pasta Templates > IaC

## Arquitetura final

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificate-/assets/28166733/f4d87fab-ab47-40b3-a6e3-680b58e3ed1c)

## arquitetura de deploy

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/0128246e-f550-4f4b-b4e6-21b756f39b03)

# AZURE APIM
1. Implemente o APIM conforme terraform terraform.apim.tf localizados no diretóro te templates de infraestrutura
2. Após criar o APIM (demora em torno de 25 minutos) você deve criar uma nova API clicando na blade de APIs e criar uma nova API em +Add API ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/74eb6831-ecf0-458a-b8a9-3df010268900)

3. Após insira as informações necessárioas para o seu entpoint, neste exemplo utilize i o displayname soma, pois na nossa aplicação "provider" possui um método post para SOMA de 2 números one iremos enviar em formato json POST
 ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/cc8d2fc2-07aa-452d-91e7-47d2e1c09875)


4. Habilite o Oauth2
   
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/e53af5cd-91c8-44ab-9884-e1059f7e2c56)

5 Após navegue até all operations e clique em base 
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/1fa7a428-b292-4f31-bdb1-27ea8b540b4c)


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


6. CLique em add operation e crie um método POST 
 ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/a4930f3e-8d7a-48b8-a4bf-247eee4c2862)

7. Para o passo 7, insira o endereço do WEBAPP backend que ficará protegido pelo APIM, no  caso deste exemplo, inserir o endereço único do seu webapp criado, no meu exemplo http://webapp-dev-apimwebappterraform.azurewebsites.net
  
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/0f7dce0b-8180-414d-9be1-2859e72d8af6)

8. Após criar o endpoint, vamos criar ao App BackEnd e o App Client para utilizarmos no APIM, para isso, acesse app registrations no painel de busca do azure e digite app registration e clique para abrir o menu 

 ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/2111a384-21fa-4624-8574-7768bafe6627)

9. Apos navegar ate app registrations, crie um novo app registration clicando em "new registration" no sinal de + 

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/50bf2066-9e79-41cd-92ba-e4bec1a635b0)  


10. Vamos criar primeiramente o app de back end,  digite o nome de sua preferência no caso deste tutorial utilizei o nome backend e clique em registrar,

     ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/ce444cb5-4c31-4bed-afe1-9c509c399925)
       


11. Após a criação você tera a página conforme abaixo exibida, copie as informações Application (client) ID e Directory (tenant) ID e cole em algum lugar pois utilizaremos esses valores quando criarmos o nosso client-app para darmos as devidas permissões

 
    ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/2e4a0f45-9dd1-49ff-96b6-0e2b877cb29c)


12. Acesse a pagina "Expose an API" no menu esquerdo e clique em "Add a scope", vocÊ será redirecionado ao menu para adicionar uma nova "URI" no exemplo nao iremos alterar o valor do Application ID URI que será semelhante ao o da figura abaixo, clique em "Save and continue"

    ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/99cc6522-5198-4a03-b16c-faeb9c1c4829)
    
13. Após clicar em "Save and continue" preencha as informações do escopo conforme abaixo

    ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/16fd37b7-f387-4e33-99ef-eddcc485cb5f)

14. Após criar a URI e Escopo, navegue até Manifest e altere o valor de "accessTokenAcceptedVersion": null para "accessTokenAcceptedVersion": 2,
    com isso poderemos utilizar o oauth2 para autenticaçaõ

    ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/531f0785-2c13-47e7-bfa6-9ac286ad4578)


16. Após criar o backend, abra uma nova aba no navegador e realize o mesmo processo para criarmos o app registration do Cliente, que será nossa API consumidora do APIM, para este app register, digite o nome de sua preferência, no caso deste tutorial utilizei o nome client-app e clique em registrar
    ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/58bb97ae-b681-45b8-aa65-019b05d61514)


17. Após a criação, preisaremos dar a permissão para o nosso client-app o backend app, va na sessão de "API permissions" e clique em "Add a permission"

    ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/e4930ea1-5eb0-4cfd-ac47-a2d77fb0b890)

18. Cole o seu Client ID que foi copiado no momento da criação do backend app no campo de busca em "APIs my organization uses" conforme abaixo, 

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/1cbc7cc4-c19e-4663-850f-2be63cbf4637)

  
19. Selecione o default e clique em Add permissions
![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/1fe20053-b8f1-44ac-989e-854c30c71ea4)

20. Assim como feito para o backend, altere o manifest também do client-app e altere o valor de "accessTokenAcceptedVersion": null para "accessTokenAcceptedVersion": 2,

 ![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/2f51049a-daba-4f7f-b48b-292754a50525)


20. NAvegue para a área de Certificates & secrets, neste menu criaremos um secret para armazenarmos em nosso APIM e adicionaremos o certificado que será utilizado pela nossa aplicação no momento da chamada dos endpoints, primeiro vamo criar o segredo, clique em Add secret e atribua um display name para ele e a data de expiração desejada, lembranod que após a expiração será necessário criar outro secret, nao esqueça de salvar o value pois só sera apresentado uma única vez, salve em algum lugar

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/f6ec84e1-2da8-489a-8161-f19176473e77)

21. Salve o segredo do campo Value

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/2ac1340e-99cd-44fa-8a98-ed69fcd7d740)

22. Agora vamos importar o nosso certificado crt, clique Upload certificate

![image](https://github.com/victorf16/PUBLIC-SpringBootAzureAuthenticationCertificateProvider/assets/28166733/335eef0e-9748-42a9-8021-8709a8c672cd)


23. 


    
    

```
function test() {
  console.log("notice the blank line before this function?");
}
```


