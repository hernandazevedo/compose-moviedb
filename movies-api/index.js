const express = require('express');
const app = express();
const PORT = 3000;

// Dados de exemplo (poderia vir de um banco de dados)
const movies = [
                 {
                   "id": 1,
                   "name": "The Shawshank Redemption",
                   "image": "https://www.imdb.com/title/tt0111161/mediaviewer/rm2311309825"
                 },
                 {
                   "id": 2,
                   "name": "The Godfather",
                   "image": "https://m.media-amazon.com/images/M/MV5BNGEwYjgwOGQtYjg5ZS00Njc1LTk2ZGEtM2QwZWQ2NjdhZTE5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"
                 },
                 {
                   "id": 3,
                   "name": "The Dark Knight",
                   "image": "https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_FMjpg_UX1000_.jpg"
                 },
                 {
                   "id": 4,
                   "name": "Pulp Fiction",
                   "image": "https://m.media-amazon.com/images/M/MV5BYTViYTE3ZGQtNDBlMC00ZTAyLTkyODMtZGRiZDg0MjA2YThkXkEyXkFqcGc@._V1_.jpg"
                 },
                 {
                   "id": 5,
                   "name": "Forrest Gump",
                   "image": "https://m.media-amazon.com/images/M/MV5BNDYwNzVjMTItZmU5YS00YjQ5LTljYjgtMjY2NDVmYWMyNWFmXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"
                 },
                 {
                   "id": 6,
                   "name": "Inception",
                   "image": "https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_.jpg"
                 },
                 {
                   "id": 7,
                   "name": "Fight Club",
                   "image": "https://m.media-amazon.com/images/M/MV5BOTgyOGQ1NDItNGU3Ny00MjU3LTg2YWEtNmEyYjBiMjI1Y2M5XkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"
                 },
                 {
                   "id": 8,
                   "name": "The Matrix",
                   "image": "https://m.media-amazon.com/images/M/MV5BN2NmN2VhMTQtMDNiOS00NDlhLTliMjgtODE2ZTY0ODQyNDRhXkEyXkFqcGc@._V1_.jpg"
                 },
                 {
                   "id": 9,
                   "name": "The Lord of the Rings: The Fellowship of the Ring",
                   "image": "https://m.media-amazon.com/images/M/MV5BNzIxMDQ2YTctNDY4MC00ZTRhLTk4ODQtMTVlOWY4NTdiYmMwXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"
                 },
                 {
                   "id": 10,
                   "name": "Star Wars: Episode IV - A New Hope",
                   "image": "https://m.media-amazon.com/images/M/MV5BOGUwMDk0Y2MtNjBlNi00NmRiLTk2MWYtMGMyMDlhYmI4ZDBjXkEyXkFqcGc@._V1_FMjpg_UX1000_.jpg"
                 }
               ]
;

// Endpoint para retornar a lista de filmes
app.get('/movies', (req, res) => {
    res.json(movies);
});

// Iniciar o servidor
app.listen(PORT, () => {
    console.log(`Server running port: ${PORT}`);
});
