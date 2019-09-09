import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
// import { googleMapsApiKey } from './assets/keys.js';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
// const key = googleMapsApiKey;

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.error(err));
