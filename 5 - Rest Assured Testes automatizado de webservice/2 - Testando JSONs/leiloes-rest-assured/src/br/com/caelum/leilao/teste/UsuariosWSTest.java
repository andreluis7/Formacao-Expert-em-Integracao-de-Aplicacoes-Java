package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;

import br.com.caelum.leilao.modelo.Usuario;

import static com.jayway.restassured.RestAssured.*;

public class UsuariosWSTest {
	private Usuario esperado1;
	private Usuario esperado2;

	@Before
	public void setup() {
		esperado1 = new Usuario(1L, "Mauricio Aniche", "mauricio.aniche@caelum.com.br");
		esperado2 = new Usuario(2L, "Guilherme Silveira", "guilherme.silveira@caelum.com.br");
	}

	@Test
	public void deveRetornarListaDeUsuarios() {
		XmlPath path = given().header("accept", "application/xml").get("/usuarios?_format=xml").andReturn().xmlPath();

		List<Usuario> usuarios = path.getList("list.usuario", Usuario.class);

		assertEquals(esperado1, usuarios.get(0));
		assertEquals(esperado2, usuarios.get(1));
	}

	@Test
	public void deveRetornarUsuarioPeloId() {
		JsonPath path = given().parameter("usuario.id", 1).header("Accept", "application/json").get("/usuarios/show")
				.andReturn().jsonPath();

		Usuario usuario = path.getObject("usuario", Usuario.class);

		assertEquals(esperado1, usuario);

	}
}
