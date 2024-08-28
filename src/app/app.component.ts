import { Component, OnInit, Inject, PLATFORM_ID } from '@angular/core';
import { RouterOutlet, Router,RouterLink, RouterModule, NavigationEnd } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { MybookingComponent } from './mybooking/mybooking.component';
import { PackageListingComponent } from './package-listing/package-listing.component';
import { FaqComponent } from './faq/faq.component';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Renderer2 } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { SessionStorageService } from './session-storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RouterLink,RouterModule,HomeComponent,MatButtonModule,MatIconModule,MatDividerModule,
    HttpClientModule, SignUpComponent,MybookingComponent,PackageListingComponent, FaqComponent, CommonModule],
  providers:[SessionStorageService],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

  currentRoute!: string;

  constructor(
    private router: Router,
    private renderer: Renderer2,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.currentRoute = event.urlAfterRedirects;
        this.changeBackgroundImage(event.urlAfterRedirects);
      }
    });
  }

  

  changeBackgroundImage(url: string) {
    if (isPlatformBrowser(this.platformId)) {
      let imageUrl = '';
      let navBGC = 'transparent';

      var container= document.getElementsByClassName('container')[0];
      var nav= document.getElementsByTagName('nav')[0];

      if(url != '/home' && url!= '/sign-up' && url!='/sign-in'){
        navBGC = '#1c4668';
      }

      switch (url) {
        case '/home':
          imageUrl = 'url(../assets/images/home-background.jpg)';
          break;
        case '/sign-up':
          imageUrl = 'url(../assets/images/sign-background.jpg)';
          break;
        case '/sign-in':
            imageUrl = 'url(../assets/images/sign-background.jpg)';
            break;
        // Add more cases as needed
        default:
          imageUrl = 'none';
          
          break;
      }

      this.renderer.setStyle(container, 'backgroundImage', imageUrl);
      this.renderer.setStyle(container, 'backgroundSize', 'cover');
      this.renderer.setStyle(container, 'backgroundRepeat', 'no-repeat');
      this.renderer.setStyle(container, 'backgroundAttachment', 'fixed');
      this.renderer.setStyle(nav, 'backgroundColor', navBGC);
    }
  }

  title = 'BonVoyage';
}
