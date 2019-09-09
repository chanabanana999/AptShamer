import { AdminService } from './services/admin.service';
import { UserService } from './services/user.service';
import { CardComponent } from './components/card/card.component';
import { ComplaintService } from 'src/app/services/complaint.service';
import { ComplexService } from './services/complex.service';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthService } from './services/auth.service';
import { BrowserModule } from '@angular/platform-browser';
import { FetchCallsService } from './services/fetch-calls.service';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgModule } from '@angular/core';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RatingPipe } from './pipes/rating.pipe';
import { RegisterComponent } from './components/register/register.component';
import { TemplateComponent } from './components/template/template.component';
import { ComplexComponent } from './components/complex/complex.component';
import { ComplaintFormComponent } from './components/complaint-form/complaint-form.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { AptComplaintsComponent } from './apt-complaints/apt-complaints.component';
import { ComplexDetailComponent } from './components/complex-detail/complex-detail.component';
import { ComplaintComponent } from './components/complaint/complaint.component';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { AgmCoreModule } from '@agm/core';
import { AdminComponent } from './components/admin/admin.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RatingPipe,
    NavigationComponent,
    TemplateComponent,
    NotFoundComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    ComplaintFormComponent,
    ComplexComponent,
    CardComponent,
    AptComplaintsComponent,
    ComplexDetailComponent,
    ComplaintComponent,
    AdminComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    FlexLayoutModule,
    GooglePlaceModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyC1QyU_ZlCoLj2GWxT-UrlV00noX0jTxVs'
    })

  ],
  providers: [
    AuthService,
    UserService,
    FetchCallsService,
    ComplexService,
    ComplaintService,
    AdminService,
    NavigationComponent,
    LoginComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
