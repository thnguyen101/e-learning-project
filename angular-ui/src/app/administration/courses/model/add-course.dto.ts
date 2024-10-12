export class AddCourseDto {
  title?: string | null;
  description?: string | null;
  thumbnailUrl?: string | null;
  benefits?: string[] | null;
  language?: string | null;
  prerequisites?: string[] | null;
  subtitles?: string[] | null;

  constructor(data: Partial<AddCourseDto>) {
    Object.assign(this, data);
  }
}