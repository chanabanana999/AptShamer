import { UserProfile } from './user-profile';
import { Complaint } from './complaint';

export class Comment {
  id: number;
  text: string;
  commentDate: Date;
  vote: number;
  complaint: Complaint;
  userProfile: UserProfile;

  constructor(id?: number, text?: string, commentDate?: Date, vote?: number,
              complaint?: Complaint, userProfile?: UserProfile) {
               this.id = id;
               this.text = text;
               this.commentDate = commentDate;
               this.vote = vote;
               this.complaint = complaint;
               this.userProfile = userProfile;
             }
}
