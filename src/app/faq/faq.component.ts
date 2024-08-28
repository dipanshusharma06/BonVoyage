import { Component } from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';


@Component({
  selector: 'app-faq',
  standalone: true,
  imports: [MatExpansionModule],
  templateUrl: './faq.component.html',
  styleUrl: './faq.component.css'
})
export class FaqComponent {
  panelOpenState = false;
  faqs = [
    {
      que: "Who are we?",
      ans: "We are a leading travel agency specializing in curated travel packages and personalized booking services. Our team is passionate about helping you explore the world with ease and comfort."
    },
    {
      que: "How do I book a travel package?",
      ans: "Booking a travel package with us is simple. Browse our website to find your desired destination and package, select your travel dates, and follow the prompts to complete your booking. You can also contact our customer service for assistance."
    },
    {
      que: "What types of travel packages do you offer?",
      ans: "We offer a variety of travel packages, including all-inclusive resorts, adventure tours, romantic getaways, family vacations, and custom itineraries. Each package is designed to provide an unforgettable travel experience."
    },
    {
      que: "How do I contact customer service?",
      ans: "You can reach our customer service team via email, phone, or live chat on our website. Our team is available 24/7 to assist you with any inquiries or issues you may have."
    }
    
  ];
  

}
