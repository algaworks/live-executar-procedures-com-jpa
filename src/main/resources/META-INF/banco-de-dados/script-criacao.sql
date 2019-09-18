create procedure nome_produto(in produto_id int, out produto_nome varchar(255)) begin select nome into produto_nome from produto where id = produto_id; end

create procedure pesquisar_produtos(in termo varchar(30)) begin select id, nome, preco from produto where upper(nome) like upper(concat(termo, '%')); end

create procedure ajustar_preco_produto(in produto_id int, in percentual_ajuste double, out preco_ajustado double) begin declare produto_preco double; select preco into produto_preco from produto where id = produto_id; set preco_ajustado = produto_preco * percentual_ajuste; update produto set preco = preco_ajustado where id = produto_id; end