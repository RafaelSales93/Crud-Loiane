
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';


import { environment } from './environments/environment';
import { enableProdMode, importProvidersFrom } from '@angular/core';
import { AppComponent } from './app/app.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { withInterceptorsFromDi, provideHttpClient } from '@angular/common/http';
import { provideAnimations } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app/app-routing.module';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserModule, bootstrapApplication } from '@angular/platform-browser';

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(AppComponent, {
    providers: [
        importProvidersFrom(BrowserModule, MatToolbarModule, AppRoutingModule, MatSnackBarModule),
        provideAnimations(),
        provideHttpClient(withInterceptorsFromDi()),
    ]
})
  .catch(err => console.error(err));
