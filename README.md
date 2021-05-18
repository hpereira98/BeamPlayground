# BeamPlayground
Boilerplate application as playground for Apache Beam applications

The next exercises are adapted from the Xpand IT's [Spark challenge](https://github.com/bdu-xpand-it/BDU-Recruitment-Challenges/wiki/Spark-2-Recruitment-Challenge)
## Datasets
Both CSV file contain scraped data from the Google Play Store.

### googleplaystore.csv
This dataset contains all the information about the the mobile applications registered in the Google Play Store.

**Columns**
- App - Application name
- Category - Category the app belongs to
- Rating - Overall user rating of the app (as when scraped)
- Reviews - Number of user reviews for the app (as when scraped)
- Size - Size of the app (as when scraped)
- Installs - Number of user downloads/installs for the app (as when scraped)
- Type - Paid or Free
- Price - Price of the app (as when scraped)
- Content Rating - Age group the app is targeted at - Children / Mature 21+ / Adult
- Genres - An app can belong to multiple genres (apart from its main category). For eg, a musical family game will belong to Music, Game, Family genres.
- Last Updated - Date when the app was last updated on Play Store (as when scraped)
- Current Ver - Current version of the app available on Play Store (as when scraped)
- Android Ver - Min required Android version (as when scraped)

### googleplaystore_user_reviews.csv
This dataset contains all the information about the users' reviews for the mobiles apps registered in the Google Play Store.

**Columns**
- App - Name of app
- Translated_Review - User review (Preprocessed and translated to English)
- Sentiment - Positive/Negative/Neutral (Preprocessed)
- Sentiment_Polarity - Sentiment polarity score
- Sentiment_Subjectivity - Sentiment subjectivity score

## Expected Pipeline
1. Read both files
2. Group apps from googleplaystore_user_reviews.csv, calculating its Average_Sentiment_Polarity
3. Filter applications from googleplaystore.csv with "Rating" greater or equal to 4.0
4. Apply the following to googleplaystore.csv:
  - App should be a unique value;
  - In case of App duplicates, the column "Categories" of the resulting row should contain an array with all the possible categories (without duplicates) for that app
  - In case of App duplicates (for all columns except categories), the remaining columns should have the same values as the ones on the row with the maximum number of reviews
  - Change the following fields:
    - Price: Convert from string to double and present the value in euros (All values are in dollars) (Consider conversion rate: 1$ = 0.9€)
    - Genres: Convert string to array of strings (delimiter: ";")
5. Add the Average_Sentiment_Polarity to the Apps list
6. Calculate the number of applications per Category, their average rating and their average sentiment polarity by genre, and save it as a parquet file with gzip compression with the name "googleplaystore_metrics"
