# Usar a imagem base do Nginx
FROM nginx:latest

# Remove a configuração padrão do Nginx
RUN rm /etc/nginx/conf.d/default.conf

# Copia a sua configuração personalizada para o Nginx
COPY default.conf /etc/nginx/conf.d/

# Copia os arquivos estáticos para o diretório padrão do Nginx
COPY lol-frontend-based /usr/share/nginx/html

# Expor a porta 80 para acesso externo
EXPOSE 80

# Comando para iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]