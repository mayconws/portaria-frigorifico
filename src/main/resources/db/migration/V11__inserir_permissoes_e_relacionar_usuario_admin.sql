INSERT INTO permissao VALUES (1, 'ADMIN');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 1);

INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES (
	(SELECT codigo FROM usuario WHERE email = 'maycon@portaria.com.br'), 1);