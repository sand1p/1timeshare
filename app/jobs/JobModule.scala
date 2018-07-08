package jobs

import play.api.inject.{SimpleModule, _}

class JobModule extends  SimpleModule(bind[ExpireSecretJob].toSelf.eagerly){
}
