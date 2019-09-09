import { Complaint } from './complaint';
export class Image {
  id: number;
  complaint: Complaint;
  imageUrl: string;

  constructor(id?: number, complaint?: Complaint, imageUrl?: string) {
    this.id = id;
    this.complaint = complaint;
    this.imageUrl = imageUrl;
  }
}
