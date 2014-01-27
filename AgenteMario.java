/**
 * Laurivan e Douglas
 * laurivan_sareta@hotmail.com
 *
 */

package br.uffs.cc.jarena;

public class AgenteMario extends Agente
{
	private int teste;
	private Boolean ficaParado=true;
	public AgenteMario(Integer x, Integer y, Integer energia) {
		super(x, y, energia);
		teste=0;
	}

	public void pensa() {

		paraAgenteId();

		teste++;

		if (getEnergia() % 40 == 0 || !podeMoverPara(getDirecao()) ) {
			if( ( getDirecao() == Agente.DIREITA || getDirecao() == Agente.ESQUERDA ) || !podeMoverPara(Agente.CIMA) ){
				setDirecao(Agente.BAIXO);
			}
		}

		if (getEnergia() % 40 == 0 || !podeMoverPara(getDirecao()) ) {
			if( ( getDirecao() == Agente.DIREITA || getDirecao() == Agente.ESQUERDA ) || !podeMoverPara(Agente.BAIXO) ){
				setDirecao(Agente.CIMA);
			}
		}

		if (getEnergia() % 40 == 0 || !podeMoverPara(getDirecao()) ) {
			if( getDirecao() == Agente.BAIXO  || !podeMoverPara(Agente.ESQUERDA) ){
				setDirecao(Agente.DIREITA);
			}
		}

		if (getEnergia() % 40 == 0 || !podeMoverPara(getDirecao()) ) {
			if( getDirecao() == Agente.BAIXO  || !podeMoverPara(Agente.DIREITA) ){
				setDirecao(Agente.ESQUERDA);
			}
		}

		if (getEnergia() % 50 == 0 || ( getEnergia() < 960 && getEnergia() > 700 && getEnergia() % 50 == 0 )) {
			setDirecao(geraDirecaoAleatoria());
		}

		paraAgenteIntervalo(500,600); //menor e maior
		paraAgenteIntervalo(400,450);
		paraAgenteIntervalo(200,350);
		paraAgenteIntervalo(0,150);
		trueFicaParado(590,600);
		trueFicaParado(490,500);
		trueFicaParado(340,350);
		trueFicaParado(140,150);
		paraAgenteId();


	}
	public void trueFicaParado(int menor, int maior){
        if(getEnergia() <= maior || getEnergia() >= menor ){
                ficaParado = true;
            }
	}
	public void paraAgenteIntervalo(int menor, int maior){
        if (getEnergia() < maior && getEnergia() > menor ) {
			if(ficaParado){
                para();
			}
		}
	}
	public void paraAgenteId(){
		if ( getId() < 17 ) {
				para();
		}
	}

	public void recebeuEnergia() {
		para();
		enviaMensagem(getX()+":"+ getY()+":"+"caregando");
	}

	public void tomouDano(int energiaRestanteInimigo) {
		para();
		if (energiaRestanteInimigo > getEnergia()) {	// quando energia do inimigo é maior que a minha nao travo batalha
			setDirecao(geraDirecaoAleatoria());
		}
	}

	public void ganhouCombate() {
		para();

	}

	public void recebeuMensagem(String msg) {
		if (msg.contains("caregando")) {
			ficaParado = false;
			int cX,cY;
			cX=Integer.parseInt(msg.substring(0,msg.indexOf(":") ) );
			cY=Integer.parseInt(msg.substring((msg.indexOf(":")+1),msg.lastIndexOf(":")));

			if ( getY() < cY && (teste%2)==0) {
				setDirecao(Agente.BAIXO);
			}else if( getY() > cY && (teste%2)==0 ){
				setDirecao(Agente.CIMA);
			}

			if ( getX() < cX && (teste%2)!=0) {
				setDirecao(Agente.DIREITA);

			}else if( getX() > cX && (teste%2)!=0 ){
				setDirecao(Agente.ESQUERDA);
			}
		}

	}

	public String getEquipe() {
		return "Marios";
	}
}
