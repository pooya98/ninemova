package com.ninemova.Network.utils

import com.ninemova.Network.repository.CommentRepository
import com.ninemova.Network.repository.FavoriteRepository
import com.ninemova.Network.repository.GenreRepository
import com.ninemova.Network.repository.LocalDataStoreRepository
import com.ninemova.Network.repository.MovieRepository
import com.ninemova.Network.repository.OpenAiRepository
import com.ninemova.Network.repository.ReplyRepository
import com.ninemova.Network.repository.UserRepository
import com.ninemova.Network.repository.YoutubeRepository
import com.ninemova.Network.repositoryimpl.CommunityRepositoryImpl
import com.ninemova.Network.repositoryimpl.FavoriteRepositoryImpl
import com.ninemova.Network.repositoryimpl.GenreRepositoryImpl
import com.ninemova.Network.repositoryimpl.MovieRepositoryImpl
import com.ninemova.Network.repositoryimpl.OpenAiRepositoryImpl
import com.ninemova.Network.repositoryimpl.ReplyRepositoryImpl
import com.ninemova.Network.repositoryimpl.UserRepositoryImpl
import com.ninemova.Network.repositoryimpl.YoutubeRepositoryImpl

class RepositoryUtils {

    companion object {
        val movieRepository: MovieRepository = MovieRepositoryImpl()
        val genreRepository: GenreRepository = GenreRepositoryImpl()
        val youtubeRepository: YoutubeRepository = YoutubeRepositoryImpl()
        val openAiRepository: OpenAiRepository = OpenAiRepositoryImpl()
        val userRepository: UserRepository = UserRepositoryImpl()
        lateinit var localDataStoreRepository: LocalDataStoreRepository
        val commentRepository: CommentRepository = CommunityRepositoryImpl()
        val replyRepository: ReplyRepository = ReplyRepositoryImpl()
        val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl()
    }
}
