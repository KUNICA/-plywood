/**
 * Created by user on 27.08.2016.
 */

function toPlug(index){
    var scriptRuller = new ScriptParticleboard();
    switch (index){
        case 1:
            scriptRuller =  new ScriptParticleboard();
            break;
        case 2:
            scriptRuller =  new ScriptPlywood();
            break;
        case 3:
            scriptRuller =  new ScriptParticleboardLaminated();
            break;
    }
    return scriptRuller;
}
