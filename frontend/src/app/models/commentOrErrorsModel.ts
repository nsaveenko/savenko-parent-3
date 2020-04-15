import {Comment} from "./Comment";

export class commentOrErrorsModel {
  commentModel: Comment;
  errors: Map<string, string> = new Map<string, string>();
}
