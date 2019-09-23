# Desafio Sky

O desafio proposto foi realizado com base no [mockup](www.google.com) e [API](http://www.google.com), entregue pela Analista de Recrutamento [Camila Conde](camila.conde@techmahindra.com).

## Arquitetura

O Projeto foi construido com base na arquitetura MVVM, utilizando Retrofit para consumir dados da API e ROOM para Salvar no banco de dados local as respostas do servidor.
Quando o usuário acessa o App, a model acessa via repositório o banco de dados (Devida a sua resposta mais rápida) enquanto a API tambem é requisitada. A tela é atualizada com os dados do banco, assim que o App recebe os dados do servidor, o banco é atualizado. Toda vez que o banco de dados é atualizado a tela também é atualizada devido ao elemento assíncrono que monitora o banco.

## Telas

A tela principal foi desenvolvida a partir do mockup, a tela de entrada(SplashScreen) e a tela de detalhes do filme foram desenvolvidas durante o desafio.
![Alt Text](https://github.com/danieloliveira138/CineSky/blob/master/device-2019-09-22-224720.png "Lista de Filmes" {width=250px height=540px})

## Funcionalidades acrescidas.

Além do Repositório local para melhorar a experiência do usuário e diminuir os tempos das chamadas na API, inclui um botão para que o usuário possa curtir os filmes, os filmes curtidos são salvos no banco e tambem são consumidos de forma assíncrona, sem prejuízo na experiência do usuário.

## Libs utilizadas.

As libs que eu utilizei são um pouco do que eu sempre carrego pra todos os projetos e no fundo do coração, faltou muita coisa, mas já tem muitas aí que me salvaram inúmera vezes.
### Libs:
1. Retrofit
2. OkHTTP
3. Gson
4. Room
5. Glide
6. Palette
7. Timber
8. LifeCicle Componentes
9. Viewpager dots indicator.
10. Stetho
11. Kotlin extensions
12. Wasabeet glide transformation

## Problemas encontrados.
Notei que havia erro na resposta de algumas imagens, acompanhei o logcat e as respostas da API e verifiquei que o link, na região do formato e tamanho da foto, está com problemas. Não sabia se isso fazia parte do desafio, pensei que pudesse ser uma forma de saber se eu me previni quanto a esses erros ou era um simples tentativa de criar um bug, pra saber se eu tinha testado o APP.
Corrigi o problema das fotos e fiz alguns testes untitários para verificar se o link vai sempre sair corretamente.
