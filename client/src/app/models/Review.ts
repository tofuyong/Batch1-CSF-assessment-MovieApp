export class Review {
    constructor(
        public title: string,
        public rating: string,
        public byline: string,
        public headline: string,
        public summary: string,
        public reviewUrl: string,
        public image: string,
        public commentCount: number
    ) { };
}