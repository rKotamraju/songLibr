//
//  Word.swift
//  Hangman
//
//  Created by Sujit Molleti on 5/26/20.
//  Copyright © 2020 Sujit Molleti. All rights reserved.
//

import Foundation


struct Word: Codable{
    var id: String = ""
    var results: [Result]?
}
