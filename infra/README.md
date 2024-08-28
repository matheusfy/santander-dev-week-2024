## TERRAFORM:
Criei alguns arquivos para subirmos o backend da aplicação para a cloud. 
No nosso cenário utilizei para hospedar a aplicação na EC2 da amazon.

** Instale adequadamente tanto o terraform quanto a AWS CLI no seu computador local. Caso não tenha uma credencial para mexer na amazon, Crie uma conta ou logue na Amazon Web Service e crie uma credencial para você configurar sua AWS CLI instalada. **


E então crie uma imagem da sua aplicação que rodará como servidor e envie-a para o dockerhub. 

Para subir a aplicação na amazon, criei um arquivo terraform. No meu caso eu subi na porta 80 do docker e 8080 para porta externa. Crie um arquivo chamado "`user_data.sh`" onde está o arquivo `main.tf` para rodar o script abaixo:

    #!/bin/bash
    sudo su
    sudo yum update -y
    sudo yum install -y docker
    sudo service docker start
    sudo usermod -a -G docker ec2-user
    docker run -e OPENAI_API_KEY=${OPENAI_API_KEY} -e GEMINI_API_KEY=${GEMINI_API_KEY} -p 80:8080 ${user}/public-api:latest

Após tudo configurado adequadamente, você pode utilizar em ordem
> terraform init \
> terraform fmt **-> para ajustar a formatação do arquivo caso queira deixar o arquivo organizado** \
> terraform validate **-> realiza a validação da configuração local apenas. Não acessa nada remoto** \
> terraform plan **-> realiza a verificação das mudanças realizadas. Caso não tenha nada rodando atualmente irá mostrar apenas o que será adicionado** \
> terraform apply **-> aplica as mudanças e realiza a criação dos recursos da aws configuradas no arquivo `.tf`**

E finalmente para caso queira destruir todos os recursos que você subiu com o arquivo 
digite: `terraform destroy`